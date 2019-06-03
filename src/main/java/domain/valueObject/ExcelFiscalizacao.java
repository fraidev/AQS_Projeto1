package domain.valueObject;

public class ExcelFiscalizacao {

    private String anoTermino;
    private String mesTermino;
    private String cnpjEstabelecimento;
    private String empresaNome;
    private String rua;
    private String cepEstabelecimento;
    private String bairroEstabelecimento;
    private String municipio;
    private String estado;

    private int columnsCount;

    public ExcelFiscalizacao(String[] excelLine){
        this.anoTermino = excelLine[0];
        this.mesTermino = excelLine[1];
        this.cnpjEstabelecimento = excelLine[2];
        this.empresaNome = excelLine[3];
        this.rua = excelLine[4];
        this.cepEstabelecimento = excelLine[5];
        this.bairroEstabelecimento = excelLine[6];
        this.municipio = excelLine[7];
        this.estado = excelLine[8];
        this.columnsCount = excelLine.length;
    }

    public String getAnoTermino() {
        return anoTermino;
    }

    public void setAnoTermino(String anoTermino) {
        this.anoTermino = anoTermino;
    }

    public String getMesTermino() {
        return mesTermino;
    }

    public void setMesTermino(String mesTermino) {
        this.mesTermino = mesTermino;
    }

    public String getCnpjEstabelecimento() {
        return cnpjEstabelecimento;
    }

    public void setCnpjEstabelecimento(String cnpjEstabelecimento) {
        this.cnpjEstabelecimento = cnpjEstabelecimento;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCepEstabelecimento() {
        return cepEstabelecimento;
    }

    public void setCepEstabelecimento(String cepEstabelecimento) {
        this.cepEstabelecimento = cepEstabelecimento;
    }

    public String getBairroEstabelecimento() {
        return bairroEstabelecimento;
    }

    public void setBairroEstabelecimento(String bairroEstabelecimento) {
        this.bairroEstabelecimento = bairroEstabelecimento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
