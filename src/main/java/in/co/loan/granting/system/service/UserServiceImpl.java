package in.co.loan.granting.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import antlr.RecognitionException;
import in.co.loan.granting.system.exception.RecordNotFoundException;
import in.co.loan.granting.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.loan.granting.system.dao.UserDAOInt;
import in.co.loan.granting.system.dto.UserDTO;
import in.co.loan.granting.system.exception.DuplicateRecordException;
import in.co.loan.granting.system.util.EmailBuilder;



@Service
public class UserServiceImpl implements UserServiceInt {

	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void add(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl Add method start");
		userRepository.save(dto);
		log.info("UserServiceImpl Add method end");
	}

	@Override
	@Transactional
	public void delete(UserDTO dto) {
		log.info("UserServiceImpl Delete method start");
		//dao.delete(dto);
		userRepository.delete(dto);
		log.info("UserServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public UserDTO findBypk(long pk) throws RecordNotFoundException {
		log.info("UserServiceImpl findBypk method start");
		//UserDTO dto = dao.findBypk(pk);
		Optional<UserDTO> optionalUserDTO = userRepository.findById(pk);
		UserDTO dto=optionalUserDTO.orElseThrow(()->new RecordNotFoundException(String.format("From the given pk: {} , no user record has been found",pk)));
		log.info("UserServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public UserDTO findByUserId(String userId) throws RecordNotFoundException {
		log.info("UserServiceImpl findByUserName method start");
		//UserDTO dto = dao.findByUserId(userId);
		Optional<UserDTO> optionalUserDTO = userRepository.findByUserId(userId);
		UserDTO dto=optionalUserDTO.orElseThrow(()->new RecordNotFoundException(String.format("From the given userId: {} , no user record has been found",userId)));
		log.info("UserServiceImpl findByUserName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl update method start");
//		UserDTO existDTO = dao.findByUserId(dto.getUserId());
//		if (existDTO != null && dto.getId() != existDTO.getId())
//			throw new DuplicateRecordException("UserId is Already Exist");
//		dao.update(dto);
		userRepository.save(dto);
		log.info("UserServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<UserDTO> list() {
		log.info("UserServiceImpl list method start");
		//List<UserDTO> list = dao.list();
		List<UserDTO> list = userRepository.findAll();
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<UserDTO> list(int pageNo, int pageSize) {
		log.info("UserServiceImpl list method start");
		//List<UserDTO> list = dao.list(pageNo, pageSize);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<UserDTO> pageResult = userRepository.findAll(pageable);
		log.info("UserServiceImpl list method end");
		return pageResult.getContent();
	}

	@Override
	@Transactional
	public List<UserDTO> search(UserDTO dto) {
		log.info("UserServiceImpl search method start");
		//List<UserDTO> list = dao.search(dto);
		List<UserDTO> list = userRepository.findByIdAndRoleIdAndFirstNameAndEmail(dto.getId(), dto.getRoleId(), dto.getFirstName(), dto.getEmail());
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		log.info("UserServiceImpl search method start");
		//List<UserDTO> list = dao.search(dto, pageNo, pageSize);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<UserDTO> pageResult = userRepository.findByIdAndRoleIdAndFirstNameAndEmail(dto.getId(), dto.getRoleId(), dto.getFirstName(), dto.getEmail(),pageable);
		log.info("UserServiceImpl search method end");
		return pageResult.getContent();
	}

	@Override
	@Transactional
	public UserDTO authentication(UserDTO dto) throws RecordNotFoundException {
		log.info("UserServiceImpl authentication method start");
		//dto = dao.authentication(dto);

		Optional<UserDTO> optionalUserDTO = userRepository.findByUserIdAndPassword(dto.getUserId(), dto.getPassword());
		optionalUserDTO.orElseThrow(()->new RecordNotFoundException(String.format(" It is not possible to update the password to the user id {}",dto.getUserId())));
		log.info("UserServiceImpl authentication method end");
		return optionalUserDTO.get();
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) throws RecordNotFoundException {
		log.info("UserServiceImpl  changePassword method start");
		UserDTO dtoExist = findBypk(id);
//		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
//			dtoExist.setPassword(newPassword);
//			dao.update(dtoExist);
//			log.info("UserServiceImpl  changePassword method end");
//			return true;
//		} else {
//			return false;
//		}

		Optional<UserDTO> optionalUserDTO = userRepository.findById(id);
		if(!optionalUserDTO.isPresent()){
			return false;
		}
		UserDTO dto = optionalUserDTO.orElseThrow(() -> new RecordNotFoundException(String.format(" It is not possible to update the password to the primary key id {}", id)));
		dto.setPassword(newPassword);
		userRepository.save(dto);
		log.info("UserServiceImpl  changePassword method end");
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean forgetPassword(String email) {

//		UserDTO dtoExist = dao.findByUserId(email);
//
//		if (dtoExist != null) {
//
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("firstName", dtoExist.getFirstName());
//			map.put("lastName", dtoExist.getLastName());
//			map.put("login", dtoExist.getUserId());
//			map.put("password", dtoExist.getPassword());
//
//			String message = EmailBuilder.getForgetPasswordMessage(map);
//
//			MimeMessage msg = mailSender.createMimeMessage();
//
//			try {
//				MimeMessageHelper helper = new MimeMessageHelper(msg);
//				helper.setTo(dtoExist.getEmail());
//				helper.setSubject("Housing Stay Forget Password ");
//				helper.setText(message, true);
//				mailSender.send(msg);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//				return false;
//			}
//		} else {
//			return false;
//		}
//		return true;
		Optional<UserDTO> dtoExist = userRepository.findByEmail(email);

		if (dtoExist.isPresent()) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("firstName", dtoExist.get().getFirstName());
			map.put("lastName", dtoExist.get().getLastName());
			map.put("login", dtoExist.get().getUserId());
			map.put("password", dtoExist.get().getPassword());

			String message = EmailBuilder.getForgetPasswordMessage(map);

			MimeMessage msg = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg);
				helper.setTo(dtoExist.get().getEmail());
				helper.setSubject("Housing Stay Forget Password ");
				helper.setText(message, true);
				mailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public long register(UserDTO dto) throws DuplicateRecordException {
		//long pk = dao.add(dto);
		UserDTO saved = userRepository.save(dto);
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("firstName", dto.getFirstName()); map.put("lastName",
		 * dto.getLastName()); map.put("login", dto.getLogin()); map.put("password",
		 * dto.getPassword()); String message = EmailBuilder.getUserRegistration(map);
		 * MimeMessage msg = mailSender.createMimeMessage(); try { MimeMessageHelper
		 * helper = new MimeMessageHelper(msg); helper.setTo(dto.getEmailId());
		 * helper.setSubject("Online Food Delivery Registration Successfully!!!");
		 * helper.setText(message, true); mailSender.send(msg); } catch
		 * (MessagingException e) { e.printStackTrace(); }
		 */
		return saved.getId();
	}
}
