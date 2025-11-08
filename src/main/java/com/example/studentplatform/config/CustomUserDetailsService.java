package com.example.studentplatform.config;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.studentplatform.model.Student;
import com.example.studentplatform.repository.StudentRepository;

@Service
public class CustomUserDetailsService //implements UserDetailsService 
{
	private final StudentRepository studentRepo;

	public CustomUserDetailsService(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String userid) throws
	 * UsernameNotFoundException { Student s = studentRepo.findById(userid)
	 * .orElseThrow(() -> new UsernameNotFoundException("User not found")); String
	 * role = s.getRoleID() == null ? "STUDENT" : s.getRoleID(); return
	 * User.builder().username(s.getEmail()).password(s.getPasswordHash())
	 * .authorities(Collections.singletonList(new
	 * SimpleGrantedAuthority(role))).build(); }
	 */
}
