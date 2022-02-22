package com.med.medservice.security;

import com.med.medservice.domain.Doctors;
import com.med.medservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final DoctorRepository doctorRepository;

    @Autowired
    public UserDetailsServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctors user = doctorRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!")
                );

        return SecurityUser.fromUser(user);
    }
}
