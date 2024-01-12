package at.qe.skeleton.tests;

import jakarta.faces.context.FacesContext;

import static org.mockito.Mockito.mock;


/**
 * This class is used to mock the FacesContext for testing purposes.
 *
 * source: <a href="https://illegalargumentexception.blogspot.com/2011/12/jsf-mocking-facescontext-for-unit-tests.html#mockFacesCurrentInstance">...</a>
 */
public abstract class ContextMocker extends FacesContext {
    public static FacesContext mockFacesContext() {
        FacesContext context = mock(FacesContext.class);
        setCurrentInstance(context);
        return context;
    }
}
