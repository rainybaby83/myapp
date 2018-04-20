//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class ExponentialMultipleFunctionDual implements RegressionFunction3 {
	private int nExps = 0;
	private double[][] xErrors = (double[][])null;
	private double[] yErrors = null;

	ExponentialMultipleFunctionDual() {
	}

	@Override
	public double[] function(double[] var1, double[] var2, int var3) {
		double[] var4 = new double[]{0.0D, 0.0D};

		int var5;
		for(var5 = 0; var5 < this.nExps; var5 += 2) {
			var4[0] += var1[var5] * Math.exp(var1[var5 + 1] * var2[0]);
		}

		if (var3 >= 0) {
			for(var5 = 0; var5 < this.nExps; var5 += 2) {
				var4[1] += var1[var5] * var1[var5 + 1] * Math.exp(var1[var5 + 1] * var2[0]);
			}

			var4[1] = this.yErrors[var3] * this.yErrors[var3] + this.xErrors[0][var3] * this.xErrors[0][var3] * var4[1] * var4[1];
		}

		return var4;
	}

	public void setNexps(int var1) {
		this.nExps = var1;
	}

	public void setXerrors(double[][] var1) {
		this.xErrors = var1;
	}

	public void setYerrors(double[] var1) {
		this.yErrors = var1;
	}
}
