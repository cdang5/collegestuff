/*File: hw2.c
 *Author: Christopher Dang
 *Original code from Professor Jon Squire 
 *modified for the purpose of hw2.
 *Output found in file, output.txt
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#undef  abs
#define abs(x) (((x)<0.0)?(-(x)):(x))
#undef  max
#define max(x,y) (((x)>(y))?((x)):(y))
#undef  min
#define min(x,y) (((x)<(y))?((x)):(y))

static void simeq(int n, double A[], double Y[], double X[]);


static int data_set(double *y, double *x) /* returns 1 for data, 0 for end  */
{                                         /* sets value of y for value of x */
  float time[20] = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6,
                   0.7, 0.8, 0.9, 1.0, 1.1, 1.2,
                   1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9};
  float thrust[20] = {0, 6, 14, 5.2, 5, 5, 5,
                      5, 4.5, 4.5, 4.5, 4.5, 4.5,
                      4, 4, 4, 4, 3.3, 3.3};

  int k = 20;
  static int i = 0;
  double xx;
  double yy;

  i++;  
  if(i>k) {i=0; return 0;}
  xx = time[i];
  yy = thrust[i];
  *x = xx;
  *y = yy;
  return 1;
} /* end data_set */

static void fit_pn(int n, double A[], double Y[], double C[]){
  /* n is number of coefficients, highest power+1 */
  int i, j;
  //k;
  double x, y;
  //t;
  double pwr[40]; /* at least n */
  
  for(i=0; i<n; i++)
    {
      for(j=0; j<n; j++)
	{
	  A[i*n+j] = 0.0;
	}
      Y[i] = 0.0;
    }
  while(data_set(&y, &x))
    {
      pwr[0] = 1.0;
      for(i=1; i<n; i++) pwr[i] = pwr[i-1]*x;
      for(i=0; i<n; i++)
	{
	  for(j=0; j<n; j++)
	    {
	      A[i*n+j] = A[i*n+j] + pwr[i]*pwr[j];
	    }
	  Y[i] = Y[i] + y*pwr[i];
	}
    }
  simeq(n, A, Y, C);
  
} /* end fit_pn */

static void check_pn(int n, double C[],
                     double *rms_err, double *avg_err, double *max_err){
  double x, y, ya, diff;
  double sumsq = 0.0;
  double sum = 0.0;
  double maxe = 0.0;
  double xmin, xmax, ymin, ymax, xbad, ybad;
  int i, k, imax;
  
  k = 0;
  while(data_set(&y, &x))
    {
      if(k==0)
    {
      xmin=x;
      xmax=x;
      ymin=y;
      ymax=y;
      imax=0;
      xbad=x;
      ybad=y;
    }
      if(x>xmax) xmax=x;
      if(x<xmin) xmin=x;
      if(y>ymax) ymax=y;
      if(y<ymin) ymin=y;
      k++;
      ya = C[n-1]*x;
      for(i=n-2; i>0; i--)
	{
	  ya = (C[i]+ya)*x;
	}
      ya = ya + C[0];
      diff = abs(y-ya);
      if(diff>maxe)
	{
	  maxe=diff;
	  imax=k;
	  xbad=k;
	  ybad=k;
	}
      sum = sum + diff;
      sumsq = sumsq + diff*diff;
    }
  *max_err = maxe;
  *avg_err = sum/(double)k;
  *rms_err = sqrt(sumsq/(double)k);
}

static void simeq(int n, double A[], double Y[], double X[]){
  double *B;           /* [n][n+1]  WORKING MATRIX */
  int *ROW;            /* ROW INTERCHANGE INDICES */
  int HOLD , I_PIVOT;  /* PIVOT INDICES */
  double PIVOT;        /* PIVOT ELEMENT VALUE */
  double ABS_PIVOT;
  int i,j,k,m;
  
  B = (double *)calloc((n+1)*(n+1), sizeof(double));
  ROW = (int *)calloc(n, sizeof(int));
  m = n+1;
  
  /* BUILD WORKING DATA STRUCTURE */
  for(i=0; i<n; i++){
    for(j=0; j<n; j++){
      B[i*m+j] = A[i*n+j];
    }
    B[i*m+n] = Y[i];
  }
  /* SET UP ROW  INTERCHANGE VECTORS */
  for(k=0; k<n; k++){
    ROW[k] = k;
  }
  
  /* BEGIN MAIN REDUCTION LOOP */
  for(k=0; k<n; k++){
    /* FIND LARGEST ELEMENT FOR PIVOT */
    PIVOT = B[ROW[k]*m+k];
    ABS_PIVOT = abs(PIVOT);
    I_PIVOT = k;
    for(i=k; i<n; i++){
      if( abs(B[ROW[i]*m+k]) > ABS_PIVOT){
	I_PIVOT = i;
	PIVOT = B[ROW[i]*m+k];
	ABS_PIVOT = abs ( PIVOT );
      }
    }
    
    /* HAVE PIVOT, INTERCHANGE ROW POINTERS */
    HOLD = ROW[k];
    ROW[k] = ROW[I_PIVOT];
    ROW[I_PIVOT] = HOLD;
    
    /* CHECK FOR NEAR SINGULAR */
    if( ABS_PIVOT < 1.0E-10 ){
      for(j=k+1; j<n+1; j++){
	B[ROW[k]*m+j] = 0.0;
      }
      printf("redundant row (singular) %d \n", ROW[k]);
    } /* singular, delete row */
      else{
	
        /* REDUCE ABOUT PIVOT */
        for(j=k+1; j<n+1; j++){
          B[ROW[k]*m+j] = B[ROW[k]*m+j] / B[ROW[k]*m+k];
        }
	
        /* INNER REDUCTION LOOP */
        for(i=0; i<n; i++){
          if( i != k){
            for(j=k+1; j<n+1; j++){
              B[ROW[i]*m+j] = B[ROW[i]*m+j] - B[ROW[i]*m+k] * B[ROW[k]*m+j];
            }
          }
        }
      }
    /* FINISHED INNER REDUCTION */
  }
  
  /* END OF MAIN REDUCTION LOOP */
  /* BUILD  X  FOR RETURN, UNSCRAMBLING ROWS */
  for(i=0; i<n; i++){
    X[i] = B[ROW[i]*m+n];
  }
  free(B);
  free(ROW);
} 

int main(int argc, char *argv[]){
  int n;
  double A[400];
  double C[20];
  double Y[20];
  double rmserr, avgerr, maxerr;
  FILE *output;
  output = fopen("output.txt", "w");

  for(int i = 3; i <=17; i++){
    n = i+1;
    printf("Fit data to %d degree polynomial\n", n-1);
    fprintf(output, "Fit data to %d degree polynomial\n", n-1);
    fit_pn(n, A, Y, C);
    check_pn(n, C, &rmserr, &avgerr, &maxerr);
    printf("maxerr=%g, avgerr=%g, rmserr=%g \n\n",
	   maxerr, avgerr, rmserr);
    fprintf(output, "maxerr=%g, avgerr=%g, rmserr=%g \n\n",
	    maxerr, avgerr, rmserr);
  }
  
  return 0;
} /* end main for least_square_fit.c */
