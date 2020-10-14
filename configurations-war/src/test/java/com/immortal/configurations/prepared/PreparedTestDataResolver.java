package com.immortal.configurations.prepared;

import org.junit.jupiter.api.extension.*;

public abstract class PreparedTestDataResolver<T>
    implements BeforeTestExecutionCallback, AfterTestExecutionCallback, ParameterResolver {
    private final static String STORE = "store";

    public abstract T create();

    public abstract void cleanup();

    public abstract Class<T> getClazz();

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        final T created = create();
        getStore(context).put(STORE, created);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        getStore(context).remove(STORE, getClazz());
        cleanup();
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == getClazz();
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return getStore(extensionContext).get(STORE);
    }
}
