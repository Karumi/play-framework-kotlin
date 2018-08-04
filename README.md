# ![Karumi logo][karumilogo] Play Framework Kotlin

This repository is used to show how to create a Play Framework project using Kotlin.

ktlint-plugin
=================
This project uses the `1.0.9` version of [kotlin-plugin][ktlint-plugin] we implemented here [pfn/kotlin-plugin#24](https://github.com/pfn/kotlin-plugin/pull/24) to run kotlin tests. 

ktlint-sbt plugin
====================
We use [ktlint-sbt](https://github.com/Karumi/ktlint-sbt) as a linter and formatter.

* Run `sbt ktlint` 

If you would like to run ktlint with formatter mode automatically before a `git commit` you can use [this pre-commit git hook](https://gist.github.com/tonilopezmr/88f651827a924993a6692b3bde2ca755). 

Running in local
================
This project runs with Play Framework, you will need to install some tools and follow some simple steps to be able to run the project locally:

1. Install `sbt`.
2. Install `docker`.
3. Run `docker-compose up -d`. This step starts postgresql database service.
4. Run `sbt run`. This will start the server itself and will reload itself whenever a new change is made to any of the project files.

Run Test
========
Run `sbt test` to run all the tests or `sbt "test-only *ClassName"` if you would like to run a single test.

Limitations
===========
* kotlin-plugin doesn't support 1.2 kotlin version yet.
* If you like to use twirl templates requires a mixed kotlin-java-scala compilation. 

License
-------

    Copyright 2018 Karumi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[karumilogo]: https://cloud.githubusercontent.com/assets/858090/11626547/e5a1dc66-9ce3-11e5-908d-537e07e82090.png
[ktlint-plugin]: https://github.com/pfn/kotlin-plugin
