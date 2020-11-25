package org.webnml;

import javax.script.ScriptEngine;

public abstract class Bind {
	
	protected ScriptEngine engine;
	
	public Bind(ScriptEngine engine) {
		this.engine = engine;
	}
	
	public ScriptEngine getEngine() {
		return engine;
	}
}
