all: main tableofcontents

main: doc_utilisateur.tex
	pdflatex doc_utilisateur.tex

tableofcontents: doc_utilisateur.tex
	pdflatex doc_utilisateur.tex

clean:
	rm *.aux *.toc *.log *.out
