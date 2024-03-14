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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class compiler_interface {

    private Label statusBar;
    private StyledText editor;
    private Canvas lineNumbers;
    private StyledText messageArea;
    private Composite editorComposite;

    public compiler_interface(Display display) {
        Shell shell = new Shell(display);
        shell.setText("Interface solicitada");
        shell.setLayout(new GridLayout(1, false));
        shell.setSize(800, 600);

        ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
        createToolItem(toolBar, "Novo");
        createToolItem(toolBar, "Abrir");
        createToolItem(toolBar, "Salvar");

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

        sashForm.setWeights(new int[]{70, 30});

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
                    int xPosition = lineNumbers.getSize().x - extent.x - 5; // Corrigido para alinhar à direita com margem
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

        // Defina a margem esquerda do editor aqui:
        int leftMarginWidth = 30;
        editor.setLeftMargin(leftMarginWidth); // Definindo a margem esquerda
    }

    private void createToolItem(ToolBar toolBar, String text) {
        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        item.setText(text);
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
