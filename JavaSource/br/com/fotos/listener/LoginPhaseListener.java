package br.com.fotos.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fotos.controller.UsuarioMB;

public class LoginPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		UsuarioMB loginBean = context.getApplication().evaluateExpressionGet(
				context, "#{usuarioMB}", UsuarioMB.class);
		if (!loginBean.isUsuarioLogado()) {
			NavigationHandler handler = context.getApplication()
					.getNavigationHandler();
			handler.handleNavigation(context, null, "login");
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
