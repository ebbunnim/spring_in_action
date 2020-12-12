package tacos.web;

import javax.validation.Valid;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderProps props; // 페이징 처리하는 구성속성 홀더 클래스 만들어 주입함 
	
	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo, OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props;
	}
	
	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
		
		// user 정보는 필드에 미리 채워서 보여주기 
		if (order.getDeliveryName()==null) {
			order.setDeliveryName(user.getFullname());
		}
		if (order.getDeliveryStreet() == null) {
			order.setDeliveryStreet(user.getStreet());
		}
		if (order.getDeliveryCity() == null) {
			order.setDeliveryCity(user.getCity());
		}	
		if (order.getDeliveryState() == null) {
			order.setDeliveryState(user.getState());
		}
		if (order.getDeliveryZip() == null) {
			order.setDeliveryZip(user.getZip());
		}	
		
		return "orderForm";
	}
	
	@GetMapping 
	public String orderForUser(@AuthenticationPrincipal User user, Model model) {
		
		
		Pageable pageable = PageRequest.of(0,props.getPageSize());
		model.addAttribute("orders",orderRepo.findByUserOrderByPlacedAtDesc(user,pageable)); // clause 만듬 
		
		return "orderList";
		
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setUser(user);
		
		orderRepo.save(order);
		sessionStatus.setComplete(); // 다음 주문에 영향 안받도록 세션 재설정 
		
		return "redirect:/";
		
	}
}
