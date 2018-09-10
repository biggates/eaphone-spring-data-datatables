package com.eaphonetech.common.datatables.jpa.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.eaphonetech.common.datatables.jpa.repository.DataTablesRepositoryImpl;

/**
 * {@link FactoryBean} creating DataTablesRepositoryFactory instances.
 * 
 * @author Damien Arrachequesne
 */
public class DataTablesRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    public DataTablesRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new DataTablesRepositoryFactory<T, ID>(entityManager);
    }

    private static class DataTablesRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

        public DataTablesRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            Class<?> repositoryInterface = metadata.getRepositoryInterface();
            if (JpaDataTablesRepository.class.isAssignableFrom(repositoryInterface)) {
                return DataTablesRepositoryImpl.class;
            } else {
                return super.getRepositoryBaseClass(metadata);
            }
        }
    }
}
