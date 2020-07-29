package di.main;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

import di.domain.RegisterRequest;
import di.exception.AlreadyExistingMemberException;
import di.exception.IdPasswordNotMatchingException;
import di.exception.MemberNotFoundException;
import di.service.ChangePasswordService2;
import di.service.MemberRegisterService2;

public class MainForSpring2 {
	
	private static GenericXmlApplicationContext ctx = null;

	public static void main(String[] args) {
		
		//ctx = new GenericXmlApplicationContext("classpath:appCtx2.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx3.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx4.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx5.xml");
		ctx = new GenericXmlApplicationContext("classpath:appCtx6.xml");
		//ctx = new GenericXmlApplicationContext("classpath:appCtx7.xml");

		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.println("명렁어를 입력하세요:");
			String command = reader.nextLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}

	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {	// new 이메일 이름 비밀번호1 비밀번호2 → 요소가 다섯개 맞는지 체크
			printHelp();
			return;
		}
		
		// Spring Container 객체 저장 타입은 Obj
		// 등록 필요
		MemberRegisterService2 regSvc = ctx.getBean("memberregSvc", MemberRegisterService2.class);
		
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		// 등록 필요
		ChangePasswordService2 changePwdSvc = ctx.getBean("memberPwSvc", ChangePasswordService2.class);
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}

}
