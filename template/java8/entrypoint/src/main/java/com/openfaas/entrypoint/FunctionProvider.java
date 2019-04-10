// Copyright (c) OpenFaaS Author(s) 2018. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.openfaas.entrypoint;

import com.openfaas.model.IHandler;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public class FunctionProvider {
    private static FunctionProvider provider;
    private ServiceLoader<IHandler> loader;

    private FunctionProvider() {
        loader = ServiceLoader.load(IHandler.class);
    }

    public static FunctionProvider getInstance() {
        if(provider == null) {
            provider = new FunctionProvider();
        }
        return provider;
    }

    public IHandler serviceImpl() {
        IHandler service = loader.iterator().next();
        if(service != null) {
            return service;
        } else {
            throw new NoSuchElementException("No IHandler implementations found");
        }
    }
}
