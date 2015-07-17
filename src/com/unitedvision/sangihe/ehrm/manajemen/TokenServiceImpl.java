package com.unitedvision.sangihe.ehrm.manajemen;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.ehrm.DateUtil;
import com.unitedvision.sangihe.ehrm.UnauthenticatedAccessException;
import com.unitedvision.sangihe.ehrm.manajemen.Token.StatusToken;
import com.unitedvision.sangihe.ehrm.manajemen.repository.OperatorRepository;
import com.unitedvision.sangihe.ehrm.manajemen.repository.TokenRepository;
import com.unitedvision.sangihe.ehrm.simpeg.Pegawai;
import com.unitedvision.sangihe.ehrm.simpeg.repository.PegawaiRepository;
import com.unitedvision.sangihe.ehrm.OutOfDateEntityException;

@Service
@Transactional(readOnly = true)
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private PegawaiRepository pegawaiRepository;
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Pegawai login(String username) {
		Pegawai pegawai = pegawaiRepository.findByNip(username);
		
		try {
			pegawai.setDaftarOperator(operatorRepository.findByPegawai(pegawai));
		} catch(PersistenceException ex) {}
		
		return pegawai;
	}

	@Override
	public Token get(String token) throws OutOfDateEntityException, UnauthenticatedAccessException {
		Token tokenObject = tokenRepository.findByToken(token);

		Pegawai pegawai = tokenObject.getpegawai();
		List<Operator> daftarOperator = operatorRepository.findByPegawai(pegawai);
		pegawai.setDaftarOperator(daftarOperator);
		
		if (tokenObject.getStatus().equals(StatusToken.LOCKED))
			throw new UnauthenticatedAccessException();

		tokenObject.extend();
		
		return tokenRepository.save(tokenObject);
	}

	@Override
	@Transactional(readOnly = false)
	public Token create(Pegawai pegawai) {
		List<Operator> daftarOperator = operatorRepository.findByPegawai(pegawai);
		pegawai.setDaftarOperator(daftarOperator);
		
		Token token = new Token();
		token.setPegawai(pegawai);
		token.setTanggalBuat(DateUtil.getNow());
		token.setStatus(StatusToken.AKTIF);
		token.generateExpireDate();
		token.generateToken();
		
		return tokenRepository.save(token);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Token create(String nip) {
		Pegawai pegawai = pegawaiRepository.findByNip(nip);
		
		return create(pegawai);
	}

	@Override
	@Transactional(readOnly = false)
	public Token lock(String token) {
		Token tokenObject = tokenRepository.findByToken(token);
		tokenObject.setStatus(StatusToken.LOCKED);
		
		return tokenRepository.save(tokenObject);
	}

}