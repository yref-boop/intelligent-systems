ann = feedforwardnet([8]);
ann.divideParam.trainRatio = 0.6;
ann.divideParam.valRatio = 0.2;
ann.divideParam.testRatio = 0.2;
[ann, tr] = train(rna, inputs, outputs);
inputsTest =  inputs(:,tr.testInd);
targetTest = outputs(:,tr.testInd);
outputsTest = sim(rna, inputsTest);
errorTest = mse(outputsTest - targetTest);

% training of single layer anns (up to 50)

meanError = [];
stdError = [];

for k=1:50,
	
	trainError = [];
	validationError = [];
	testError = [];

	for i=1:50,
		ann = feedforwardnet([k]);
		ann.trainParam.showWindow = false;
		[ann tr] = train(ann, inputs, outputs);
		trainError(end+1)		= tr.best_perf;
		validationError(end+1)	= tr.best_vperf;
		testError(end+1)		= tr.best_tperf;
	end;

	meanError(end+1) 	= mean(testError);
	stdError(end+1)		= std(testError);
	fprintf('%i', k);
end;

% training of double layer anns (from [1 1] to [10 10])

meanError2 = [];
stdError2 = [];

for j=1:20,
	for k=1:20,
		trainError = [];
		validationError = [];
		testError = [];
		for i = 1:30,
			ann = feedforwardnet([k j]);
			ann.trainParam.showWindow = false;
			[ann tr] = train(ann, inputs, outputs);
			trainError(end+1)		= tr.best_perf;
			validationError(end+1)	= tr.best_vperf;
			testError(end+1)		= tr.best_tperf;
		end;
		meanError2(end+1) 	= mean(testError);
		stdError2(end+1)	= std(testError);
		fprintf('%i',j);
		fprintf('%i\n',k);
	end;
end;



















