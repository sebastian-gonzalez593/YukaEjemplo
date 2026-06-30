package problema1_juegoderoles;

public interface Problema1_IDefensor {
    void defender(int dano);
    void recibirDanoDirecto(int dano);
    void curar(int cantidad);
}