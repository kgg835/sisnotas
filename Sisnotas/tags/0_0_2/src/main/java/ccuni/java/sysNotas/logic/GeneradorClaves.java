package ccuni.java.sysNotas.logic;

import java.util.Random;

public class GeneradorClaves
{
    private int longitud;

    public GeneradorClaves(int longitud)
    {
        this.longitud = longitud;
    }

    public String generarClave()
    {
        char[] clave = new char[longitud];
        char[] letras = new char[26];
        Random ran = new Random();
        int ind = 0;
        for (char c = 'a'; c <= 'z'; c++)
        {
            letras[ind++] = c;
        }
        for (int i = 0; i < longitud; i++)
        {
            int letra;
            letra = (int) (ran.nextDouble() * 2);
            if (letra == 1)
            {
                clave[i] = letras[(int) (ran.nextDouble() * 26)];
            }
            else
            {
                int num = (int) (ran.nextDouble() * 10);
                clave[i] = Character.forDigit(num, 10);
            }
        }
        return new String(clave);
    }

    public static void main(String[] args)
    {
        GeneradorClaves g = new GeneradorClaves(8);
        System.out.println(g.generarClave());
        System.out.println(new String(g.generarClave()));
    }
}
