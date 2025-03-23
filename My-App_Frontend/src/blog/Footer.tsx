import * as React from 'react';
import Link from '@mui/material/Link';
import GitHubIcon from '@mui/icons-material/GitHub';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import { Grid2ClassKey } from '@mui/material';
import { Container, Grid, Paper, Box, Typography, CssBaseline, ThemeProvider } from '@mui/material';

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Material UI SAS, trading as MUI
      </Link>{' '}
      {new Date().getFullYear()}
      {'. '}
    </Typography>
  );
}

interface FooterProps {
  description: string;
  title: string;
}

export default function Footer(props: FooterProps) {
  const { description, title } = props;

  const socialLinks = [
    { name: 'GitHub', url: 'https://github.com/ceajay09', Icon: GitHubIcon },
    { name: 'LinkedIn', url: 'https://linkedin.com/in/cesar-jaquiéry-9215aa179', Icon: LinkedInIcon },
  ];

  return (
    <Box component="footer" sx={{ bgcolor: 'background.paper', py: 6 }}>
      <Container maxWidth="lg">
        <Typography variant="h6" align="center" gutterBottom>
          {title}
        </Typography>
        <Grid container alignItems="center" justifyContent="center" >
          {/* Linkes Icon */}
          <Grid item xs>
            <Link href={socialLinks[0].url} target="_blank" sx={{ display: 'flex', justifyContent: 'flex-end' }}>
              {React.createElement(socialLinks[0].Icon, { style: { fontSize: '50px' } })}
            </Link>
          </Grid>
          <Grid item xs={6}>
            <Typography
              variant="subtitle1"
              align="center"
              color="text.secondary"
              component="p"
            >
              {/* {description} */}
              <Link href="mailto:c.jaquiery@gmail.com">
                c.jaquiery@gmail.com
              </Link>
            </Typography>
          </Grid>
          <Grid item xs>
            <Link href={socialLinks[1].url} target="_blank" sx={{ display: 'flex', justifyContent: 'flex-start' }}>
              {React.createElement(socialLinks[1].Icon, { style: { fontSize: '50px' } })}
            </Link>
          </Grid>
        </Grid>
        <Copyright />
      </Container>
    </Box>
  );
}
