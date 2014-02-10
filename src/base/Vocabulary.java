package base;

public class Vocabulary {
	private String vocabulario;
	
	public Vocabulary (String v){
		vocabulario=v;
	}
	
	public String getVocabulario() {
		return vocabulario;
	}

	public void setVocabulario(String vocabulario) {
		this.vocabulario = vocabulario;
	}

	public String toString(){
		return vocabulario;
	}
}
