import { AngularOnboardPage } from './app.po';

describe('angular-onboard App', () => {
  let page: AngularOnboardPage;

  beforeEach(() => {
    page = new AngularOnboardPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
