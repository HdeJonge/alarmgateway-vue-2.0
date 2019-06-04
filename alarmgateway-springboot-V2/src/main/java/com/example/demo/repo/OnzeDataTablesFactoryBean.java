package com.example.demo.repo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class OnzeDataTablesFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, ID> {

	public OnzeDataTablesFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new OnzeDataTablesRepositoryFactory<T, ID>(entityManager);
	}

	private static class OnzeDataTablesRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

		public OnzeDataTablesRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			Class<?> repositoryInterface = metadata.getRepositoryInterface();
			if (OnzeDataTablesRepository.class.isAssignableFrom(repositoryInterface)) {
				return OnzeDataTablesRepositoryImpl.class;
			} else {
				return super.getRepositoryBaseClass(metadata);
			}
		}
	}
}
