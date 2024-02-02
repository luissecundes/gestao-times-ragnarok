package br.com.duxusdesafio.exceptions;


public class Exceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public Exceptions(String message) {
		super(message);
	}

	public static class NotFoundException extends Exceptions {
		private static final long serialVersionUID = 1L;

		public NotFoundException() {
			super("Não foi possível encontrar a lista...");
		}
	}
	public static class NotFoundIdException extends Exceptions {
		private static final long serialVersionUID = 1L;

		public NotFoundIdException(long l) {
			super("Não foi possível encontrar a lista...");
		}
	}

	public static class NotSavedException extends Exceptions {
		private static final long serialVersionUID = 1L;
		public NotSavedException() {
			super("Não foi possível efetuar o cadastro!!");
		}
	}

	public static class NotUpdatedException extends Exceptions {
		private static final long serialVersionUID = 1L;
		public NotUpdatedException(Long integranteId) {
			super("Não foi possível atualizar o cadastro!!!");
		}
	}

	public static class NotDeletedException extends Exceptions {
		private static final long serialVersionUID = 1L;
		public NotDeletedException(String string) {
			super("Não foi possível fazer a exclusão!!!");
		}
	}
}

