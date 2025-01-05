/**
 * @author bruno
 *
 */
public class Conta {

    private int numero;
    private double saldo;
    private double limite = 100;
    private double[] extrato = new double[10];
    private int operacoesRealizadas = 0;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }


    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo + this.limite;
    }


    public double getLimite() {
        return limite;
    }

    public boolean sacar(double valor) {
        if (valor < 0 || valor > getSaldo()) {
            return false;

        } else if (operacoesRealizadas >= 10) {
            return false;

        } else if (valor <= saldo) {
            saldo = saldo - valor;
            extrato[operacoesRealizadas++] = -valor;
            return true;

        } else {
            double restante = valor - saldo;
            saldo = 0;
            limite -= restante;
            extrato[operacoesRealizadas++] = -valor;
            return true;
        }
    }


    public boolean depositar(double valor) {
        if (valor < 0) {
            return false;

        }
        if (operacoesRealizadas >= 10) {
            return false;

        } else if (valor <= (100 - limite)) {
            limite = valor + limite;
            extrato[operacoesRealizadas++] = +valor;
            return true;

        } else {
            double valorDeLimite = 100 - limite;
            limite = 100;
            saldo = saldo + valor - (valorDeLimite);
            extrato[operacoesRealizadas++] = +valor;
            return true;
        }
    }


    public boolean transferir(Conta destino, double valor) {
        if (valor <= 0 ){
            return false;

        }if (valor>getSaldo()){
            return false;

        }if (operacoesRealizadas >= 10) {
            return false;

        }if (valor>saldo) {
            double restante = valor - saldo;
            limite -= restante;
            saldo = 0;
        }else {
            saldo -= valor;
        }
        destino.depositar(valor);
        extrato[operacoesRealizadas++] = -valor;
        return true;

        }



    public double[] verExtrato() {

        double[] extratoAtual = new double[operacoesRealizadas];
        for (int i = 0; i < operacoesRealizadas; i++) {
            extratoAtual[i] = extrato[i];
        }
        return extratoAtual;
    }

    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo=" + saldo +
                ", limite=" + limite +
                '}';

    }
}