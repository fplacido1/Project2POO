package VCSystem;

import java.time.LocalDate;

public interface Revision {

	int getNum();

	String getAuthor();

	LocalDate getDate();

	String getComment();

	Artefacts getArtefact();
}
