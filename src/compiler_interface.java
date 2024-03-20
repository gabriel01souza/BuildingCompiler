import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

public class compiler_interface<Buttons> {

	static Display display = null;
	private String currentFilePath = null;
	private Shell shell;
	private Label statusBar;
	private StyledText editor;
	private Canvas lineNumbers;
	private StyledText messageArea;
	private Composite editorComposite;
	private Button btnNewButton;
	private Canvas canvas;
	private Button btnAbrir;
	private Button btnCopiar;
	private Button btnColar;
	private Button btnRecortar;
	private Button btnCompilar;
	private Button btnEquipe;

	public compiler_interface(Display display) {
		shell = new Shell(display);
		shell.setSize(882, 512);
		shell.setText("Compilador");
		shell.setMinimumSize(900, 600);
		configureKeyListeners();
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		canvas = new Canvas(shell, SWT.NONE);
		canvas.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_canvas = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_canvas.widthHint = 126;
		canvas.setLayoutData(gd_canvas);
		
		// CRIAÇÃO DOS BOTÕES
		createButtons();
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		editorComposite = new Composite(sashForm, SWT.NONE);
		GridLayout gl_editorComposite = new GridLayout(2, false);
		gl_editorComposite.marginWidth = 0;
		editorComposite.setLayout(gl_editorComposite);
		editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		lineNumbers = new Canvas(editorComposite, SWT.NONE);
		GridData lineNumbersData = new GridData(SWT.LEFT, SWT.TOP, false, true);
		lineNumbersData.heightHint = 48;
		lineNumbersData.widthHint = 23; // Ajuste a largura
		lineNumbers.setLayoutData(lineNumbersData);

		editor = new StyledText(editorComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		editor.setWrapIndent(1);
		GridData gd_editor = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_editor.heightHint = 218;
		editor.setLayoutData(gd_editor);
		new Label(editorComposite, SWT.NONE);
		
				messageArea = new StyledText(editorComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
				messageArea.setLeftMargin(20);
				messageArea.setEditable(false);
				messageArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		sashForm.setWeights(new int[] { 70 });
		new Label(shell, SWT.NONE);

		statusBar = new Label(shell, SWT.BORDER);
		statusBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		statusBar.setText("Barra de status");

		configureLineNumbers();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void configureLineNumbers() {
		editor.addPaintListener(e -> {
			GC gc = e.gc;
			int lineHeight = editor.getLineHeight();
			int visibleLines = editor.getClientArea().height / lineHeight + 5;
			int topIndex = editor.getTopIndex();

			for (int i = 0; i < visibleLines; i++) {
				int line = topIndex + i;
				if (line < editor.getLineCount()) {
					String lineNumber = String.valueOf(line + 1);
					Point extent = gc.stringExtent(lineNumber);
					int xPosition = lineNumbers.getSize().x - extent.x - 5; // Corrigido para alinhar à direita com
																			// margem
					gc.drawString(lineNumber, xPosition, i * lineHeight);
				}
			}
		});

		editor.getVerticalBar().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lineNumbers.redraw();
			}
		});

		// margem esquerda do editor;
		int leftMarginWidth = 30;
		editor.setLeftMargin(leftMarginWidth); // Definindo a margem esquerda
	}

	private void createButtons() {
		String[][] buttonOptions = { { "Novo", "Novo [ctrl-n]" }, { "Abrir", "Abrir [ctrl-o]" },
				{ "Salvar", "Salvar [ctrl-s]" }, { "Copiar", "Copiar [ctrl-c]" }, { "Colar", "Colar [ctrl-v]" },
				{ "Recortar", "Recortar [ctrl-x]" }, { "Compilar", "Compilar [F7]" }, { "Equipe", "Equipe [F1]" } };

		for (String[] option : buttonOptions) {
			createButton(option[0], option[1]);
		}
	}

	private void createButton(String command, String text) {

		Button btnNovoFile = new Button(canvas, SWT.NONE);
		btnNovoFile.setLayoutData(new RowData(120, 50));
		btnNovoFile.setText(text);
		btnNovoFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				performAction(command);
			}
		});
	}

	private void configureKeyListeners() {
		Display.getCurrent().addFilter(SWT.KeyDown, event -> {
			if ((event.stateMask & SWT.CTRL) != 0) {
				switch (event.keyCode) {
				case 'n':
					performAction("Novo");
					break;
				case 'o':
					performAction("Abrir");
					break;
				case 's':
					performAction("Salvar");
					break;
				case 'c':
					performAction("Copiar");
					break;
				case 'v':
					performAction("Colar");
					break;
				case 'x':
					performAction("Recortar");
					break;
				}
			} else if (event.keyCode == SWT.F1) {
				performAction("Equipe");
			} else if (event.keyCode == SWT.F7) {
				performAction("Compilar");
			}
		});
	}

	private void performAction(String command) {
		switch (command) {
		case "Novo":
			editor.setText("");
			messageArea.setText("");
			statusBar.setText("Pronto");
			currentFilePath = "";
			break;
		case "Abrir":
			abrirArquivo();
			break;
		case "Salvar":
			salvarArquivo();
			break;
		case "Copiar":
			editor.copy();
			break;
		case "Colar":
			editor.paste();
			break;
		case "Recortar":
			editor.cut();
			break;
		case "Compilar":
			messageArea.setText("Compilação de programas ainda não foi implementada");
			break;
		case "Equipe": // Ordem alfabetica
			messageArea.setText(
					"Equipe de desenvolvimento: Daniel de Paula, Gabriel Cardoso de Souza, Umberto Oliveira de Araújo Neto Leonetti");
			break;
		}
	}

	private void abrirArquivo() {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.txt" });
		currentFilePath = dialog.open();
		if (currentFilePath != null) {
			try {
				String content = new String(Files.readAllBytes(Path.of(currentFilePath)));
				editor.setText(content);
				messageArea.setText("");
				statusBar.setText("Arquivo aberto: " + currentFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void salvarArquivo() {
		if (currentFilePath == null || currentFilePath.isEmpty()) {
			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.txt" });
			String path = dialog.open();
			if (path != null && !path.isEmpty()) {
				currentFilePath = path;
			}
		}

		if (currentFilePath != null && !currentFilePath.isEmpty()) {
			try {
				Files.writeString(Path.of(currentFilePath), editor.getText());
				messageArea.setText("");
				statusBar.setText("Arquivo salvo: " + currentFilePath);
			} catch (IOException e) {
				e.printStackTrace();
				messageArea.setText("Erro ao salvar o arquivo: " + e.getMessage());
			}
		}

	}

	public void updateStatusBar(String filePath) {
		statusBar.setText("Aberto: " + filePath);
	}

	public static void main(String[] args) {
		display = new Display();
		new compiler_interface(display);
		display.dispose();
	}
}
