ann = feedforwardnet([8]);
ann.divideParam.trainRatio = 0.6;
ann.divideParam.valRatio = 0.2;
ann.divideParam.testRatio = 0.2;
[ann, tr] = train(rna, inputs, outputs);
inputsTest =  inputs(:,tr.testInd);
targetTest = outputs(:,tr.testInd);
outputsTest = sim(rna, inputsTest);
errorTest = mse(outputsTest - targetTest);



trainError = [];
validationError = [];
testError = [];

for i=1:50,
	ann = feedforwardnet([8]);
	[ann tr] = train(ann, inputs, outputs);
	trainError(end+1)		= tr.best_perf;
	validationError(end+1)	= tr.best_vperf;
	testError(end+1)		= tr.best_tperf;
end;

meanError = mean(testError);
stdError = std(errorTest);
