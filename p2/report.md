# report
an analysis of the efficiency of different ann architectures for this problem has been made:
in order to reduce the effects of the non-deterministic nature of anns, each architecture has been trained several times and the mean and standard deviation of the error have been considered

### single hidden layer
#### method
for the analysis of architectures with one hidden layer, the efficiency of all neural networks with neuron count in [1,50] is taken into account

to avoid noise difficulting the analysis of the data, each architecture is trained 50 times and the mean and standard deviation taken as representatives of the efficiency of that architecture

matlab's default stopping criteria are the ones used for this training:
- maximum epochs:		1000
- max training time:	inf
- performance goal:		0
- min gradient:			1e-07
- max validation check:	6
- mu:					1e+10

#### error
<img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/single_mean.png" width= 300>

while it is true that there is some noise present, this data shows a relatively easy to read trend:

error declines in a logarithmic fashion as the number of neurons increases, starting at a value slightly bigger than 20 and declining rapidly, but decreasing that speed until a value close to 15 is reached, where the error stabilizes on a value bounded by [6, 7.5]

this is a relatively good result since the values we are dealing with are close to 140, thus implying a percentage of error close to 5% (with possible deviation of +-1%)


#### standard deviation
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/single_std.png" width= 300>

the data obtained on the standard deviation seems to be quite affected by noise, or at least does not seem to follow any clear pattern

this code has been run several times and the pattern does seem to repeat: the standard deviation varies wildly, and the low values on the first 15 iterations does not always apply, thus we cannot use it as a guide on the efficiency of the architectures

#### decision

with all of this information, the architecture chosen was [13], since it is one of the architectures with smallest neuron count that reaches the lowest error 

in this specific example, the standard deviation is also quite low, but std does not seem to be a reliable measure

##### results


### double hidden layer
#### method
for the analysis of architectures with two hidden layers, the efficiency of all neural networks with neuron count in [1,20] for each layer is taken into account

to avoid noise difficulting the analysis of the data, each architecture is trained 30 times and the mean and standard deviation taken as representatives of the efficiency of that architecture
this value is not bigger due to time constraints

in order to decide the rate in which each layer changes neurons, a mock training test with values [1,10] was made
the conclusion reached is that increasing the first layer first ([1,1],[2,1],...,[10,1],[1,2],[2,2],...[9,10],[10,10]) lead to easier to read data when plotted

matlab's default stopping criteria are the ones we used for this training:
- maximum epochs:		1000
- max training time:	inf
- performance goal:		0
- min gradient:			1e-07
- max validation check:	6
- mu:					1e+10


#### error
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/double_mean.png" width= 300>

in this case, some error is present, but it is really easy to see the pattern that the data follows:

the error decreases rapidly as the number of neurons on the first layer increases, once it goes back to 1, the error shows an spike
on each of the 20 times this process is iterated, the lowest point of the error is lower
overall, two patterns can be easily seen:
- the one detailed here, which happens once each 20 steps, a rapid decline followed by an spike
- another one similar to the one on the analysis of the anns with single hidden layer: the overall error on each iteration decrases in a logarithmic function, this time it happens around the value [10,10]

moreover, the value of the error seems to stabilize as the number of neurons grows bigger [10,10], in a similar fashion as the anns with a single hidden layer, where the value of the error is also bounded by [6, 7.5], but this time with an anomaly on the first 3 values where the error grows several times larger


#### standard deviation
 <img src="https://github.com/yref-boop/intelligent-systems/blob/main/p2/results/double_std.png" width= 300>

the data obtained regarding standard deviation does also show some noise, but there is an apparent pattern that follows closely the error obtained on the mean:

for each spike on the error mean, there is another on the standard deviation, that implies that there has clearly been higher and lower values on those spikes and that those architectures are not reliable at all

this function also follows both the patterns defined on the previous mean error

#### decision

with this information it is clear that any configuration where any of the two layers has a number equal or lower to 3 does not suffice, neither the accuracy (mean) or reliability (standard deviation), moreover, an architecture whose values are closest to the best but with a lower number of neurons is the one to be selected
the architecture [9,11] seems to satisfy those conditions

##### results

### conclusion

#### extra regards:
- on most, if not all cases, the stopping criteria met was the number of validation checks
- the values have been reduced on double hidden layer due to time constraints
- after some manual test, the default training options were chosen
- all of this data and training has been done on matlab online
