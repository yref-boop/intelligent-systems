# report
an analysis of the efficiency of different ann architectures for this problem has been made:
in order to reduce the effects of the non-deterministic nature of anns, each architecture has been trained several times and the mean and standard deviation of the error have been considered

### single hidden layer
#### method
or a single hidden layer, the range of neurons was [1,50], each ann trained 50 times
the results show some expected noise, but does not impede the analysis of the data obtained
#### error
<img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/single_mean.png" width= 300>
the overall trend is to the test error to decline, in a logarithmic function, stabilizing in a error value bounded by [6, 7.5]
this stabilization becomes apparent for values >15

#### standard deviation
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/single_std.png" width= 300>
moreover, the standard deviation of the error is more affected by noise, taking into account the data gathered it seems to actually become less constant or more noise-prone as the number of neurons grows: a possible explanation for this would be that as the number of connections increases, the ann has more possible values, thus enabling more chaothic results
knowing this, the architecture chosen was

### double hidden layer
#### method
- for two hidden layers, the range of neurons on each layer was [1,20], each ann trained 30 times 
the results show some expected noise, more than the previous study , but does not impede the analysis of the data obtained
since two values are at play, we first made a mock with values [1,10] to see which data was clearer to analyze, and increasing first the first layer and then the second showed easier to read results

#### error
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/double_mean.png" width= 300>
yet again, the overall trend of the error is to go down as the used numbers increase (if plotted, the spikes represent training a [1 n] ann, thus the increase in error) and it also stabilizes in a value in [6, 7]
#### standard deviation
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/double_std.png" width= 300>
moreover, the standard deviation seems to be decreasing as the number of  neurons on the first layer increases, the same with the second, 
knowing this, the architecture chosen was

on most, if not all cases, the stopping criteria met was the number of validation checks,
the training stopped on different epochs, from numbers close to 70 to, even on some (rare) cases, on numbers lower than 10, the apparent parent is: 

some extra regards:
- the values have been reduced on double hidden layer due to time constraints
- after some manual test, the default training options were chosen
- all of this data and training has been done on matlab online
