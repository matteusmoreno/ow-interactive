package br.com.matteusmoreno.ow_interactive.exception;

public class TransactionOwnershipException extends RuntimeException {

    public TransactionOwnershipException() {
        super("This transaction is not from this user");
    }
}
