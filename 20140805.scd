{SinOsc.ar}.play;

{Saw.ar}.play;

{|chaos=1.5|
	Crackle.ar(chaos)}.play(args:[\chaos,2]);

{RHPF.ar(WhiteNoise.ar,200,015,3,1)}.play;


(
f={
	|freq1=440,freq2=440,amp1=0.5,amp2=0.5|

	var sine1,sine2,output;

	sine1 = SinOsc.ar(freq1,mul:amp1);
	sine2 = SinOsc.ar(freq2,mul:amp2);

	output=sine1+sine2;
}
)
f.play;

{
	s=Server.boot;
	s.boot;
}

f.play(args: [\freq2, 550,\amp1, 0.25]);


{
	Stethoscope.new(s,2,0);
	FreqScope.new();
}

//SinWave

{Pan2.ar(SinOsc.ar(440,0,1,0))}.plot
// {Pan2.ar(SinOsc.ar(100,0,1,0))}.play;
{Pan2.ar(SinOsc.ar(100,0,1,0))}.play;



{[SinOsc.ar(440,0,1,0),SinOsc.ar(440,1pi,1,0)]}.plot


{[SinOsc.ar(440,0,1,0),SinOsc.ar(440,~phase,1,0)]}.play

{SinOsc.ar(440,0,1,0)+SinOsc.ar(440,1pi,1,0)}.play

//スレテオで聴こえる
{[SinOsc.ar(440,0,1,0),SinOsc.ar(440,1pi,1,0)]}.play



~phase=1pi;
~phase=0;
{Pan2.ar(SinOsc.ar(440,0,1,0) + SinOsc.ar(440,~phase,1,0),0,-1)}.play

~phase = 0;
~phase = 1pi;

{Pan2.ar(SinOsc.ar(440, 0, 1, 0) + SinOsc.ar(440, ~phase, 1, 0), 0, 1)}.play


{
	//オシロスコープ作成
	Stethoscope.new(s,2,0);
	//スペクトラムアナライザ作成
	FreqScope.new(512,300,0);
}


{Pan2.ar(SinOsc(440,0,1,0)+SinOsc(440,1pi/2,1,0))}.play;
//change phase
{Pan2.ar(SinOsc.ar(440,0,2,0)+SinOsc.ar(440,1pi,1,0),0,1)}.play;
//change multiply
{Pan2.ar(SinOsc.ar(441,0,1,0)+SinOsc(440,1pi,1,0),0,1)}.play;
//change frequency
{Pan2.ar(SinOsc.ar(450,0,1,0) + SinOsc.ar(440,1pi,1,0),0,1)}.play;
{Pan2.ar(SinOsc.ar(500,0,1,0) + SinOsc.ar(480,1pi,1,0),0,1)}.play;
{Pan2.ar(SinOsc.ar(441,0,1,0) + SinOsc.ar(480,0,1,0),0,1)}.play;

//add 5 sinwave

{Pan2.ar(
	SinOsc.ar(440,0,1,0)+
	SinOsc.ar(441,0,1,0)+
	SinOsc.ar(480,0,1,0)+
	SinOsc.ar(570,0,1,0)+
	SinOsc.ar(1000,0,1,0)
	,0,1)}.play;
//add 10!!
{Pan2.ar(
	SinOsc.ar(220,0,1,0)+
	SinOsc.ar(330,0,1,0)+
	SinOsc.ar(440,0,1,0)+
	SinOsc.ar(550,0,1,0)+
	SinOsc.ar(660,0,1,0)+
	SinOsc.ar(440,0,1,0)+
	SinOsc.ar(770,0,1,0)+
	SinOsc.ar(880,0,1,0)+
	SinOsc.ar(990,0,1,0)+
	SinOsc.ar(1000,0,1,0)+
	SinOsc.ar(1100,0,1,0)
	,0,1)}.play;