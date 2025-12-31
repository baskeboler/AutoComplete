# AutoComplete
This is the source code for the library.

## New in 3.4

* Introduced the `TextSession` interface to decouple the core auto-completion
  engine from Swing text components.
* Added `SwingTextSessionAdapter` for existing Swing entry points and a
  no-op `AutoCompletionUI` strategy for headless or console-driven use cases.
* A minimal console demo (`ConsoleAutoCompleteDemo`) shows how to connect
  the engine to a `PlainDocument` and print completions without any Swing
  dependencies.
