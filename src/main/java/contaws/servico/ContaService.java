package contaws.servico;

import java.net.URI;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import contaws.dao.ContaDao;
import contaws.dao.MovimentoDao;
import contaws.modelo.Conta;
import contaws.modelo.Movimento;

@Path("/conta")
public class ContaService {
	
	@Context UriInfo uriInfo;

	@POST
	@Path("/deposito")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response deposito(JAXBElement<Movimento>  dado) { 

		Movimento movimento = dado.getValue();
		
		ContaDao dao = new ContaDao();
		Conta c = dao.buscarConta(movimento.getConta().getNumero(), 
				                  movimento.getConta().getAgencia());
		movimento.setConta(c);
		
		MovimentoDao mDao = new MovimentoDao();
		mDao.deposito(movimento);			
		try {
			int idNovoRecurso = 1;			
			String novoRecurso = uriInfo.getPath() + "/" + idNovoRecurso;
			URI uri = new URI(novoRecurso) ;				
			return Response.created(uri).build();				
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return Response.serverError().build();
    }  
}
