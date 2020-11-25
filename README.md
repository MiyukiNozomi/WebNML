# WebNML
JavaScript as a HTML Pre Processor.

Well, in the future this thing will use a custom javascript.

# WebNML Example

index.html
```
<nmlscript>
	io.fadd("header.html");
  io.include("Something.wnml");
</nmlscript>

<nmlscript>
	server.println("<a href=\"https://github.com/MiyukiNozomi/WebNML/\">Oh Look! this come form a WebNML Script!</a>");
  doSomething();
</nmlscript>

<nmlscript>
	io.fadd("footer.html");
</nmlscript>
```
Pure WebNML Files
```
function doSomething(){
   server.println("Click In");
}
```
