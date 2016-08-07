I'm using images of game objects created by MillionthVector, all credabillity to MillionthVector.

I'm also using images created by Korba from the community Open Game Art, complete credabillity to Korba.

# ParallaX Physics Engine

ParallaX is a 2D game/2D game engine, written in Java. It is generically built for scalabillity. The idéa was to se how far I could go with as little help from libraries as possible. For rendering I'm using the Slick 2D library, all credabillity to the author of that library, and the critical intersect-algoritm is also vastly inspired by the one found i Slick2D.


The program mainly consists of three top level classes which are `Entity`, which can produce `Impact`, and has a subclass sprite which holds any form of `Aim`. Entitiy has `Part` subclasses among which we find `PartForce`, which can be used as gravity, `PartLiquid`, which can be used as friction and different types of firm materia, solid and exploadable, which can utilize bouncyness and other interactions with normals as done for classes under `EntityAgile`. All Game Objects interact by sending impacts trough classes which implement `Impact` to each other if appliccable, and there is for instance no assumption the gravity runs downwards, which enables the user to create it's own maps if she or he would like, with all these components.

There is also a (currently) very simple GUI component implementation for use in pure graphical contexts.


The project is a work in progress, and eventually there might be a javascript version of it, more aimed towards having a game play, and even a C++ version of the parts which could be extracted to an API or even some kind of lib.
