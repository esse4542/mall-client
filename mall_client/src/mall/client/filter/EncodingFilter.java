package mall.client.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") //  모든 요청을 가로챌 수 있다 ( * : 모든 정보 )
public class EncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("EncodingFilter 실행");
		request.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
	}

}