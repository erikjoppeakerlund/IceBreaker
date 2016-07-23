# Ice Breaker

Ice Breaker is a 2D game/2D game engine, written in Java. It is generically built for scalabillity. The idéa was to se how far I could go with as little help from libraries as possible. For rendering I'm using the Slick 2D library, all credabillity to the author of that library, and the critical intersect-algoritm is also vastly inspired by the one found i Slick2D.


The program mainly consists of three top level classes which are `GameObject`, which can produce `Impact`, and has a subclass sprite which holds any form of `Aim`. Game Object has `WorlBuilder` subclasses among which we find `WorldBuilderForce`, which can be used as gravity, `WorldBuilderLiquid`, which can be used as friction and different types of firm materia, solid and exploadable, which can utilize bounciness and other interactions with normals as done for classes under `GameObjectFalling`. All Game Objects interact by sending impacts trough classes which implement `Impact` to each other if appliccable, and there is for instance no assumption the gravity runs downwards, which enables the user to create it's own maps if she or he would like, with all these components.


The project is a work in progress, and eventually there might be a javascript version of it.
