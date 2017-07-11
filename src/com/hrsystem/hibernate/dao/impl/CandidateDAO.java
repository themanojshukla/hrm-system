package com.hrsystem.hibernate.dao.impl;

public class CandidateDAO /*implements ICandidateDao */{
	/*private Candidate candidate;

	public CandidateDAO(Candidate candidate) {
		this.candidate = candidate;
	}
	
	//@Override
	public boolean isExisting() {
		boolean returnValue = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate");
	    EntityManager manager = factory.createEntityManager( );
	    Query query = manager.createQuery("Select count(*) FROM candidate where email = ?");
	    query.setParameter("email", candidate.getEmail());
	    int count = (int) query.getSingleResult();
	    if(count > 0){
	    	returnValue = true;
	    }
	    manager.close( );
	    factory.close( );  
	    return returnValue;
	}

	@Override
	public void addNewCandidate() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate");
	    EntityManager manager = factory.createEntityManager( );
		manager.getTransaction().begin();
		manager.persist(candidate);
		manager.close();
		factory.close();
	}

	public boolean isExistingByEmail(String email) {
		
		return false;
	}

	@Override
	public int getIdByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
}