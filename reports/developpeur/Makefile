all: main tableofcontents

main: doc_developpeur.tex
pdflatex doc_developpeur.tex

tableofcontents: doc_developpeur.tex
pdflatex doc_developpeur.tex

clean:
rm *.aux *.toc *.log *.out
