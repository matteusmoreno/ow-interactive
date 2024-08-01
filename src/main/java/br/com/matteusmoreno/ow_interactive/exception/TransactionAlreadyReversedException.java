package br.com.matteusmoreno.ow_interactive.exception;

public class TransactionAlreadyReversedException extends RuntimeException {

    public TransactionAlreadyReversedException()
    {
        super("Transaction already reversed");
    }
}
