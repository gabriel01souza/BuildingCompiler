import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

public class compiler_interface {

	static Display display = null;
	private String currentFilePath = null;
	private Shell shell;
	private Label statusBar;
	private StyledText editor;
	private Canvas lineNumbers;
	private StyledText messageArea;
	private Composite editorComposite;

	public compiler_interface(Display display) {
		shell = new Shell(display);
		shell.setSize(882, 512);
		shell.setText("Compilador");
		GridLayout gl_shell = new GridLayout(2, false);
		gl_shell.marginBottom = 1;
		shell.setLayout(gl_shell);
		shell.setMinimumSize(900, 600);
		configureKeyListeners();
		new Label(shell, SWT.NONE);
				new Label(shell, SWT.NONE);
		
				ToolBar toolBar = new ToolBar(shell, SWT.BORDER | SWT.FLAT | SWT.WRAP | SWT.RIGHT | SWT.SHADOW_OUT | SWT.VERTICAL);
				GridData gd_toolBar = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
				gd_toolBar.heightHint = 100;
				gd_toolBar.minimumWidth = 570;
				gd_toolBar.minimumHeight = 150;
				gd_toolBar.widthHint = 154;
				toolBar.setLayoutData(gd_toolBar);
				createToolItems(toolBar);

		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		editorComposite = new Composite(sashForm, SWT.NONE);
		GridLayout gl_editorComposite = new GridLayout(2, false);
		gl_editorComposite.marginWidth = 0;
		editorComposite.setLayout(gl_editorComposite);
		editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		lineNumbers = new Canvas(editorComposite, SWT.NONE);
		GridData lineNumbersData = new GridData(SWT.LEFT, SWT.TOP, false, true);
		lineNumbersData.widthHint = 20; // Ajuste a largura
		lineNumbers.setLayoutData(lineNumbersData);

		editor = new StyledText(editorComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
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
		statusBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
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
		editor.setLeftMargin(20); // Definindo a margem esquerda
	}

	private void createToolItems(ToolBar toolBar) {
		String[][] toolItems = { { "Novo", "Novo [ctrl-n]" }, { "Abrir", "Abrir [ctrl-o]" },
				{ "Salvar", "Salvar [ctrl-s]" }, { "Copiar", "Copiar [ctrl-c]" }, { "Colar", "Colar [ctrl-v]" },
				{ "Recortar", "Recortar [ctrl-x]" }, { "Compilar", "Compilar [F7]" }, { "Equipe", "Equipe [F1]" } };

		for (String[] item : toolItems) {
			createToolItem(toolBar, item[0], item[1]);
		}
	}

	private void createToolItem(ToolBar toolBar, String command, String text) {
		
		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        Image image = new Image(display,"imgs/novo.png");
        item.setImage(image);
		item.setText(text);
		item.addSelectionListener(new SelectionAdapter() {
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
