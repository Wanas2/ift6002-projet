package ca.ulaval.glo4002.vet;

import org.springframework.boot.SpringApplication;

public class VetServer implements Runnable {
	private String[] args;

	public static void main(String[] args) {
		new VetServer(args).run();
	}

	public VetServer(String[] args) {
		this.args = args;
	}

	@Override
	public void run() {
		SpringApplication.run(VetSpringApplication.class, args);
	}
}
