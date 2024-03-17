import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.swt.SWT;
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
	
	private String currentFilePath = null;
	private Shell shell;
	private Label statusBar;
	private StyledText editor;
	private Canvas lineNumbers;
	private StyledText messageArea;
	private Composite editorComposite;

	public compiler_interface(Display display) {
		shell = new Shell(display);
		shell.setText("Interface solicitada");
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(800, 600);

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		createToolItems(toolBar);

		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		editorComposite = new Composite(sashForm, SWT.NONE);
		editorComposite.setLayout(new GridLayout(2, false));
		editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		lineNumbers = new Canvas(editorComposite, SWT.NONE);
		GridData lineNumbersData = new GridData(SWT.LEFT, SWT.FILL, false, true);
		lineNumbersData.widthHint = 25; // Ajuste a largura
		lineNumbers.setLayoutData(lineNumbersData);

		editor = new StyledText(editorComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		messageArea = new StyledText(sashForm, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		messageArea.setEditable(false);
		messageArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		sashForm.setWeights(new int[] { 70, 30 });

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
		editor.setLeftMargin(leftMarginWidth); // Definindo a margem esquerda
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
		item.setText(text);
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				performAction(command);
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
        case "Equipe":            //Ordem alfabetica
            messageArea.setText("Equipe de desenvolvimento: Daniel de Paula, Gabriel Cardoso de Souza, Umberto Oliveira de Araújo Neto Leonetti");
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
		    dialog.setFilterExtensions(new String[]{"*.txt"});
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
		Display display = new Display();
		new compiler_interface(display);
		display.dispose();
	}

}
