package com.example.demo.springDataJpaAuditing.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
//public class AuditAwareImpl implements AuditorAware<Long> {
//
//    /* Con esto devolvemos el id del usuario desde el SecurityContex
//    * Ya que no tenemos spring security, comentamos esta parte
//    */
//
//    @Override
//    public Optional<Long> getCurrentAuditor() {
//        ApplicationUser principal = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return Optional.of(principal.getId());
//    }
//}
