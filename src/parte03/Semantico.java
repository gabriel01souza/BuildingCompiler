package parte03;

public class Semantico implements Constants
{
    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("A��o #"+action+", Token: "+token);
    }	
}
