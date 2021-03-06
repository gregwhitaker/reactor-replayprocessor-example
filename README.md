# reactor-replayprocessor-example
![Build](https://github.com/gregwhitaker/reactor-replayprocessor-example/workflows/Build/badge.svg)

Example of using a ReplayProcessor to store and replay history between subscriptions.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build
    
## Running the Example
Follow the steps below to run the example:

1. Run the following command to start the service:

        ./gradlew bootRun
        
   If successful, you will see the count starting in the terminal:
   
       : onNext(1)
       : onNext(2)
       : onNext(3)
       
2. Open a web browser and navigate to [http://localhost:8080/nums](http://localhost:8080/nums).

    Notice that all previously generated numbers are streamed to you before you receive new numbers.
    
3. Next, navigate to [http://localhost:8080/nums?history=false](http://localhost:8080/nums?history=false).

    Notice that the count started wherever it had left off and did not return historical numbers.

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/reactor-replayprocessor-example/issues).

## License
MIT License

Copyright (c) 2020 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
