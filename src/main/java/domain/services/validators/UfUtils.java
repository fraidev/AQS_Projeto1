package domain.services.validators;

import java.util.HashMap;

public class UfUtils {
    HashMap<String, String> estados = new HashMap<>();

    public UfUtils(){
        estados.put("Acre", "AC");
        estados.put("Alagoas", "AL");
        estados.put("Amapá", "AP");
        estados.put("Amazonas", "AM" );
        estados.put("Bahia", "BA" );
        estados.put("Ceará", "CE");
        estados.put("Distrito Federal", "DF" );
        estados.put("Espírito Santo","ES" );
        estados.put("Goiás", "GO" );
        estados.put("Maranhão", "MA" );
        estados.put("Mato Grosso", "MT" );
        estados.put("Mato Grosso do Sul", "MS" );
        estados.put("Minas Gerais", "MG" );
        estados.put("Pará","PA" );
        estados.put("Paraíba","PB" );
        estados.put("Paraná","PR");
        estados.put("Pernambuco","PE");
        estados.put("Piauí", "PI" );
        estados.put("Rio de Janeiro", "RJ" );
        estados.put("Rio Grande do Norte", "RN");
        estados.put("Rio Grande do Sul", "RS");
        estados.put("Rondônia", "RO");
        estados.put("Roraima", "RR");
        estados.put("Santa Catarina", "SC");
        estados.put("São Paulo", "SP");
        estados.put("Sergipe", "SE");
        estados.put("Tocantins", "TO");
    }


    public String getSigla(String uf) throws Exception {
        if (estados.containsKey(uf)) {
            String estado = estados.get(uf);
            return estado;
        }
        throw new Exception("Estado não encontrado");
    }
}
