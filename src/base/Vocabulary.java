package base;

public class Vocabulary {
	private String vocabulario;
	private Object s; 
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.vocabulario != null ? this.vocabulario.hashCode() : 0);
        hash = 59 * hash + (this.s != null ? this.s.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vocabulary other = (Vocabulary) obj;
        if ((this.vocabulario == null) ? (other.vocabulario != null) : !this.vocabulario.equals(other.vocabulario)) {
            return false;
        }
        if (this.s != other.s && (this.s == null || !this.s.equals(other.s))) {
           return false;
        }
        return true;
    }

}
