s = Server.default.boot;
s.boot;

{
    SinOsc.ar(100,SinOsc.ar(XLine.kr(1,1000,9),0,2pi),0.25)
}.scope


(
    z={
        arg noiseHz=8;
        var freq,amp,sig;
        freq = LFNoise0.kr(noiseHz).exprange(200,1000);
        amp=LFNoise1.kr(12).exprange(0.02,1);
        sig=SinOsc.ar(freq) * amp;
    }.play;
)
z.free;




#############################################################
#Tutorial2   http://www.youtube.com/watch?v=oTBcGPXH6K0   ###
#############################################################
(
SynthDef.new(\sineTest,{
    arg noiseHz=8;
    var freq,amp,sig;
    freq=LFNoise0.kr(noiseHz).exprange(200,1000);
    amp =LFNoise0.kr(12).exprange(0.02,1);
    sig=SinOsc.ar(freq) * amp;
    Out.ar(0,sig);
}).add;
)

x=Synth.new(\sineTest);

x.set(\noiseHx,12);o
x.free;



###########################################################
#Tutorial 3 http://www.youtube.com/watch?v=LKGGWsXyiyo ####
###########################################################
(
    SynthDef.new(\pulseTest,{
        arg ampHz=4, fund=40,maxPartial=4,width=0.5;
        var amp1, amp2, freq1,freq2,sig1,sig2;
        amp1= LFPulse.kr(ampHz,0,0.12) * 0.75;
        amp2= LFPulse.kr(ampHz,0.5,0.12) * 0.75;
        freq1= LFNoise0.kr(4).exprange(fund,fund * maxPartial).round(fund);
        freq2= LFNoise0.kr(4).exprange(fund,fund * maxPartial).round(fund);
        freq1= freq1 * LFPulse.kr(8,add:1);
        freq2= freq2 * LFPulse.kr(6,add:1);
        sig1=Pulse.ar(freq1,width,amp1);
        sig2=Pulse.ar(freq2,width,amp2);
        sig1=FreeVerb.ar(sig1,0.7,0.8,0.25);
        sig2=FreeVerb.ar(sig2,0.7,0.8,0.25);
        Out.ar(0,sig1);
        Out.ar(1,sig2);
    }).add;
)

x=Synth.new(\pulseTest);
x.set(\width,0.015);
x.set(\width,0.02);
x.set(\maxPartial,100);
x.set(\fund,10);
x.set(\ampHz,1000);
x.free;



###########################################################
#Tutorial 4 http://www.youtube.com/watch?v=-wDAPo9hpCg ####
###########################################################

{
    x= {
        var sig,freq, env;
        env = XLine.kr(1,0.01,5,doneAction:2);
        freq= XLine.kr(880,110,1,doneAction:0);
        sig = Pulse.ar(freq) * env.dbamp;
    };
}


0.125.ampdb
x.play;
x.free;
s.freeAll;


###########################################################
#Tutorial 5 http://www.youtube.com/watch?v=fAXETAyrv8s ####
###########################################################


(
    x = {
        arg t_gate=0;
        var sig,env;
        env = EnvGen.kr(Env.new([0,1,0.2,0],[0.5,1,2],[3,-3,0]), t_gate,doneAction:2);
        sig = Pulse.ar(LFPulse.kr(8).range(600,900)) * env;
    }.scope;
)
x.set(\t_gate,1);
x.free;
Env.new([0,1,0.2,0],[0.5,1,2],[3,-3,0]).plot;



(
    x = {
        arg gate=0;
        var sig,env,freq;
        freq = EnvGen.kr(Env.adsr(1),gate,200,0.1);
        env = EnvGen.kr(Env.adsr,gate,doneAction:2);
        sig = Pulse.ar(SinOsc.kr(freq).range(50,100)) * env;
    }.scope;
)
x.set(\gate,0);
x.free;


###########################################################
#Tutorial 6 http://www.youtube.com/watch?v=bMGXYEg1gJo ####
###########################################################


(
x = Array.newClear(5);
[6,4,0.5,10,7].do{
	arg item,count;
	x[count] = item.squared.postln;
}
);
x;

z = [6,4,0.5,10,5].collect(_.squared);
5.do{arg item;item.postln};


x ={VarSaw.ar(40!2,0,0.05)}.play;

x.free;

(
SynthDef.new(\iter,{
	arg freq = 40;
	var temp,sum,env;
	sum = 0;
	env = EnvGen.kr(
		Env.perc(0.01,5,1,-2),
		doneAction:1
	);
	10.do{
		temp = VarSaw.ar(
			freq * {Rand(0.99,1.02)}!2,
			{Rand(0.0,1.0)}!2,
			{ExpRand(0.005,0.05)}!2
		);
		sum = sum + temp;
	};
	sum  = sum * 0.05 * env;
	Out.ar(0,sum);
}).add;
)

x =Synth.new(\iter,[\freq,60]);
x =Synth.new(\iter,[\freq,73].midicps);
x =Synth.new(\iter,[\freq,80].midicps);
x =Synth.new(\iter,[\freq,75].midicsp);

(
[53,59,63,68].do{
	arg midinote;
	Synth.new(\iter,[\freq,midinote.midicps]);
}
)


(
SynthDef.new(\iter2,{
	arg freq = 200,dev =1.02, num =10;
	var temp,sum;
	sum =0;
	num.do{
		arg count;
		temp = SinOsc.ar(
			freq *
			(count + 1) *
			LFNoise1.kr({Rand(0.05,0.2)}!2).range(dev.reciprocal,dev)
		);
		temp =temp * LFNoise1.kr({Rand(0.5,8)}!2).exprange(0.01,1);
		sum = sum + temp;
	};
	sum = sum * 0.05;
	Out.ar(0,sum);
}).add;
)
x =  Synth.new(\iter2);
x.set(\freq,50);
x.set(\dev,5.4);
x.free;

