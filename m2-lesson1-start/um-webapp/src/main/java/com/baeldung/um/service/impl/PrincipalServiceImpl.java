package com.baeldung.um.service.impl;

import com.baeldung.common.persistence.service.AbstractService;
import com.baeldung.common.security.SpringSecurityUtil;
import com.baeldung.um.persistence.dao.IPrincipalJpaDao;
import com.baeldung.um.persistence.model.Principal;
import com.baeldung.um.service.IPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrincipalServiceImpl extends AbstractService<Principal> implements IPrincipalService {

    @Autowired
    private IPrincipalJpaDao dao;

    public PrincipalServiceImpl() {
        super();
    }

    // API

    // find

    @Override
    public Principal findOneByName(String name) {
        return findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Principal findByName(final String name) {
        return dao.findByName(name);
    }

    // other

    @Override
    @Transactional(readOnly = true)
    public Principal getCurrentPrincipal() {
        final String principalName = SpringSecurityUtil.getNameOfCurrentPrincipal();
        return getDao().findByName(principalName);
    }

    // Spring

    @Override
    protected final IPrincipalJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Principal> getSpecificationExecutor() {
        return dao;
    }

}
