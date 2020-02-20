package br.com.javaparaweb.financeiro.web.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.javaparaweb.financeiro.util.HibernateUtil;

@WebFilter(urlPatterns = {"*.jsf"})
public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sf;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException {
		Session currentSession = this.sf.getCurrentSession();
		
		Transaction transaction = null;
		
		try {
			transaction = currentSession.beginTransaction();
			chain.doFilter(request, response);
			transaction.commit();
			if(currentSession.isOpen()) {
				currentSession.close();
			}
		}catch (Throwable e) {
			try {
				if(transaction.isActive()) {
					transaction.rollback();
				}
			}catch (Throwable e1) {
				e1.printStackTrace();
			}
			throw new ServletException(e);
		}		
	}//fim diFilter

	@Override
	public void destroy() {
		//
	}//fim destroy

}//fim da classe
