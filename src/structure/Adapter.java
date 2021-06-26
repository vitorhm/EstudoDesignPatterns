package structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Adapter {
}

interface FormaGeometrica {
    public void desenha();
    public void redimensiona();
}

class Triangulo implements FormaGeometrica {

    @Override
    public void desenha() {
        System.out.println("Desenha triângulo");
    }

    @Override
    public void redimensiona() {

    }
}

class Circulo implements FormaGeometrica {

    @Override
    public void desenha() {

    }

    @Override
    public void redimensiona() {

    }
}

class GerenciaFormaGeometrica {

    private List<FormaGeometrica> formaGeometricaList = new ArrayList<>(
            Arrays.asList(
                    new Triangulo(),
                    new Circulo(),
                    new RetanguloAdapter(new Retangulo())
            )
    );

    public void desenhaFormas() {

        formaGeometricaList.forEach(FormaGeometrica::desenha);

    }

}

interface FormaGeometricaApi {
    public void desenhaForma();
    public void redimensionaForma();
}

class Retangulo implements FormaGeometricaApi {

    @Override
    public void desenhaForma() {

    }

    @Override
    public void redimensionaForma() {

    }
}

class RetanguloAdapter implements FormaGeometrica {

    private Retangulo retangulo;

    public RetanguloAdapter(Retangulo retangulo) {
        this.retangulo = retangulo;
    }

    @Override
    public void desenha() {
        this.retangulo.desenhaForma();
    }

    @Override
    public void redimensiona() {
        this.retangulo.redimensionaForma();
    }
}